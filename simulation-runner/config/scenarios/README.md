## Scenario configuration files

This directory contains "scenario" configuration files.  These files are used to
describe an actual gatling run; they reference one or more
["node" config files](../nodes) to determine which agent recordings to play
back, and they also set up the parameters for each type of agent/node (e.g. how
many instances of that node to simulate simultaneously, etc.).

### File format

Here is an example "scenario" configuration file:

```json
{
    "run_description": "'medium' role from perf control repo",
    "nodes": [
        {
            "node_config": "PECouchPerfMedium.json",
            "num_instances": 1250,
            "ramp_up_duration_seconds": 1800,
            "num_repetitions": 4,
            "sleep_duration_seconds": 1800
        }
    ]
}
```

TODO TODO TODO