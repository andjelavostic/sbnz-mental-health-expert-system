# Demo scripts

Python scripts for preparing defense/demo examples through the running Spring API.

Start the backend first, then run from the repository root:

```powershell
python demo-scripts\demo_assessments.py low
python demo-scripts\demo_assessments.py noncep
python demo-scripts\demo_assessments.py cep-seed --reset
python demo-scripts\demo_assessments.py cep --reset --backward
```

Scenarios:

- `low` sends one low-risk assessment.
- `noncep` sends one high-risk assessment based on current answers only.
- `cep-seed` sends only the first two historical assessments. Use this before submitting the final assessment from the frontend.
- `cep` sends three assessments for the same user with timestamps at `now - 25h`, `now - 12h`, and `now`. This acts as a pseudo-clock demo for the `StressEscalationEvent` CEP rule.
- `all` runs all scenarios.
- `--reset` deletes previous demo assessments and decisions for that user before sending the scenario.

The default `userId` is `1`, because the current history page loads history for user `1`.

You can override it:

```powershell
python demo-scripts\demo_assessments.py cep --user-id 5
```

Frontend CEP demo:

1. Start the backend and frontend.
2. Run `python demo-scripts\demo_assessments.py cep-seed --reset`.
3. Open the survey form for user `1`.
4. Fill the form with the values below.
5. Submit the form.

Expected result: `GENERALIZED_ANXIETY_TENDENCY`, `HIGH`, with `CEP` inside `triggeredPatterns`.

Use these answers for the final frontend assessment:

| Field | Answer |
| --- | --- |
| Stress level | `5` |
| Emotional exhaustion | `Da` |
| Nervousness | `5` |
| Overload feeling | `Da` |
| Low mood frequency | `3` |
| Mood swings | `1` |
| Sadness level | `3` |
| Loss of control feeling | `Ne` |
| Irritability | `Ne` |
| Worry frequency | `5` |

| Sleep hours | `8` |
| Sleep problems | `Ne` |
| Night awakenings | `Ne` |
| Rested after sleep | `Da` |
| Chronic fatigue | `Ne` |
| Stress headaches | `Ne` |
| Appetite changes | `Ne` |
| Low energy | `Ne` |
| Physical exhaustion | `1` |

| Concentration problems | `1` |
| Forgetfulness | `1` |
| Decision difficulty | `1` |
| Mental confusion | `1` |
| Productivity drop | `1` |
| Attention span issues | `1` |
| Mental fatigue | `1` |

| Social avoidance | `1` |
| Communication withdrawal | `1` |
| Social isolation feeling | `1` |
| Loss of interest | `1` |
| Family avoidance | `1` |
| Time spent alone | `1` |
| Emotional distance | `1` |

| Work pressure | `1` |
| Financial problems | `Ne` |
| Recent stress event | `Ne` |
| Relationship issues | `Ne` |
| Lack of rest time | `1` |
| Constant pressure | `5` |
| Rumination on tasks | `5` |

| Symptom duration | `60` |
| Isolation duration | `14` |
| Exhaustion duration | `20` |
| Panic frequency | `5` |
| Stress trend | `5` |
| Mood degradation trend | `5` |
| Productivity decline trend | `1` |
