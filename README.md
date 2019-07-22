# HermesTestDemo

### Time-consuming comparison between JSC and Hermes (both using cached Instance)

#### ScreenShots
|  JSC   | Hermes  |
|  ----  | ----  |
| ![JSC](./docs/JSC-time.gif)  | ![Hermes](./docs/Hermes-time.gif) |
| 单元格  | 单元格 |

#### chart
![chart](./docs/JSCvsHermesTimeCost.png)


### How to run this demo?

#### Run with Hermes
```
# gradle.properties
useHermesEngine=true

# shell
./gradlew installDebug
```

#### Run with JSC
```
# gradle.properties
useHermesEngine=false

# shell
./gradlew installDebug
```
