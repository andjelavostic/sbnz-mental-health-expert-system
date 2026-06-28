# Demo scripts

Python scripts for preparing defense/demo examples through the running Spring API.

Start the backend first, then run from the repository root:

```powershell
python demo-scripts\demo_assessments.py low
python demo-scripts\demo_assessments.py noncep
python demo-scripts\demo_assessments.py cep --backward
```

Scenarios:

- `low` sends one low-risk assessment.
- `noncep` sends one high-risk assessment based on current answers only.
- `cep` sends three assessments for the same user with timestamps at `now - 24h`, `now - 12h`, and `now`. This acts as a pseudo-clock demo for CEP time windows.
- `all` runs all scenarios.

The default `userId` is `1`, because the current history page loads history for user `1`.

You can override it:

```powershell
python demo-scripts\demo_assessments.py cep --user-id 5
```
