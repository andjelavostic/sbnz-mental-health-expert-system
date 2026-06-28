import argparse
import json
import sys
from datetime import datetime, timedelta
from urllib.error import HTTPError, URLError
from urllib.request import Request, urlopen


API_URL = "http://localhost:8080/api/rules/evaluate"
BACKWARD_URL = "http://localhost:8080/api/rules/backward-check"


def iso(dt):
    return dt.replace(microsecond=0).isoformat()


def base_assessment(user_id, timestamp):
    return {
        "userId": user_id,
        "timestamp": iso(timestamp),
        "stressLevel": 1,
        "emotionalExhaustion": 0,
        "nervousness": 1,
        "overloadFeeling": 0,
        "lowMoodFrequency": 1,
        "moodSwings": 1,
        "sadnessLevel": 1,
        "lossOfControlFeeling": 0,
        "irritability": 0,
        "worryFrequency": 1,
        "sleepHours": 8,
        "sleepProblems": 0,
        "nightAwakenings": 0,
        "restedAfterSleep": 1,
        "chronicFatigue": 0,
        "stressHeadaches": 0,
        "appetiteChanges": 0,
        "lowEnergy": 0,
        "physicalExhaustion": 1,
        "concentrationProblems": 1,
        "forgetfulness": 1,
        "decisionDifficulty": 1,
        "mentalConfusion": 1,
        "productivityDrop": 1,
        "attentionSpanIssues": 1,
        "mentalFatigue": 1,
        "socialAvoidance": 1,
        "communicationWithdrawal": 1,
        "socialIsolationFeeling": 1,
        "lossOfInterest": 1,
        "familyAvoidance": 1,
        "timeSpentAlone": 1,
        "emotionalDistance": 1,
        "workPressure": 1,
        "financialProblems": 0,
        "recentStressEvent": 0,
        "relationshipIssues": 0,
        "lackOfRestTime": 5,
        "constantPressure": 1,
        "ruminationOnTasks": 1,
        "symptomDuration": 1,
        "isolationDuration": 1,
        "exhaustionDuration": 1,
        "panicFrequency": 0,
        "stressTrend": 1,
        "moodDegradationTrend": 1,
        "productivityDeclineTrend": 1,
    }


def with_values(assessment, **values):
    updated = dict(assessment)
    updated.update(values)
    return updated


def post_json(url, payload):
    data = json.dumps(payload).encode("utf-8")
    request = Request(url, data=data, headers={"Content-Type": "application/json"}, method="POST")
    with urlopen(request, timeout=20) as response:
        body = response.read().decode("utf-8")
        return json.loads(body)


def print_result(label, result):
    print(f"\n{label}")
    print(f"  finalState: {result.get('finalState')}")
    print(f"  severity: {result.get('severity')}")
    print(f"  score: {result.get('score')}")
    print(f"  triggeredPatterns: {result.get('triggeredPatterns')}")


def low_risk(user_id):
    now = datetime.now()
    return [base_assessment(user_id, now)]


def non_cep_high_risk(user_id):
    now = datetime.now()
    return [
        with_values(
            base_assessment(user_id, now),
            stressLevel=5,
            emotionalExhaustion=1,
            nervousness=5,
            overloadFeeling=1,
            lowMoodFrequency=4,
            sadnessLevel=4,
            lossOfControlFeeling=1,
            irritability=1,
            worryFrequency=5,
            sleepHours=4,
            sleepProblems=1,
            nightAwakenings=1,
            restedAfterSleep=0,
            chronicFatigue=1,
            lowEnergy=1,
            physicalExhaustion=5,
            concentrationProblems=4,
            forgetfulness=4,
            decisionDifficulty=4,
            mentalConfusion=4,
            productivityDrop=4,
            attentionSpanIssues=4,
            mentalFatigue=4,
            workPressure=5,
            constantPressure=5,
            ruminationOnTasks=5,
            symptomDuration=20,
            exhaustionDuration=20,
            stressTrend=4,
            moodDegradationTrend=4,
            productivityDeclineTrend=4,
        )
    ]


def cep_anxiety_escalation(user_id):
    now = datetime.now()
    return [
        with_values(
            base_assessment(user_id, now - timedelta(hours=24)),
            stressLevel=2,
            nervousness=2,
            worryFrequency=2,
            moodSwings=1,
            productivityDeclineTrend=4,
            moodDegradationTrend=4,
            panicFrequency=3,
        ),
        with_values(
            base_assessment(user_id, now - timedelta(hours=12)),
            stressLevel=3,
            nervousness=3,
            worryFrequency=3,
            moodSwings=1,
            productivityDeclineTrend=4,
            moodDegradationTrend=4,
            panicFrequency=3,
        ),
        with_values(
            base_assessment(user_id, now),
            stressLevel=5,
            emotionalExhaustion=1,
            nervousness=5,
            overloadFeeling=1,
            worryFrequency=5,
            moodSwings=5,
            irritability=1,
            productivityDeclineTrend=5,
            moodDegradationTrend=5,
            exhaustionDuration=20,
            isolationDuration=20,
            panicFrequency=5,
            stressTrend=5,
        ),
    ]


SCENARIOS = {
    "low": low_risk,
    "noncep": non_cep_high_risk,
    "cep": cep_anxiety_escalation,
}


def run_scenario(name, user_id):
    assessments = SCENARIOS[name](user_id)
    last_result = None
    for index, assessment in enumerate(assessments, start=1):
        last_result = post_json(API_URL, assessment)
        print_result(f"{name} assessment {index}/{len(assessments)}", last_result)
    return assessments[-1], last_result


def run_backward_check(assessment, target_state):
    result = post_json(BACKWARD_URL, {"assessment": assessment, "targetState": target_state})
    print("\nBackward check")
    print(f"  targetState: {result.get('targetState')}")
    print(f"  confirmed: {result.get('confirmed')}")
    print(f"  cepBased: {result.get('cepBased')}")
    print(f"  confirmedEvidence: {result.get('confirmedEvidence')}")


def main():
    parser = argparse.ArgumentParser(description="Demo data generator for the mental health expert system.")
    parser.add_argument("scenario", choices=[*SCENARIOS.keys(), "all"])
    parser.add_argument("--user-id", type=int, default=1)
    parser.add_argument("--backward", action="store_true")
    parser.add_argument("--target", default="ANXIETY_ESCALATION_STATE")
    args = parser.parse_args()

    try:
        if args.scenario == "all":
            for scenario in SCENARIOS:
                assessment, _ = run_scenario(scenario, args.user_id)
        else:
            assessment, _ = run_scenario(args.scenario, args.user_id)

        if args.backward:
            run_backward_check(assessment, args.target)
    except HTTPError as error:
        print(f"HTTP error {error.code}: {error.read().decode('utf-8')}", file=sys.stderr)
        return 1
    except URLError as error:
        print(f"Cannot connect to backend: {error}", file=sys.stderr)
        print("Start the Spring service on http://localhost:8080 first.", file=sys.stderr)
        return 1

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
