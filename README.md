# Custom services test
This repo aims to show how to quickly define and use custom services in rosjava.

## Pre-requisites
You will need to install and source rosjava build tools to use this.
See https://github.com/rosjava for reference (installation).

## Installation and usage
- Clone the repo inside a workspace:
```
mkdir -p ~/rosjava_test_ws/src
cd ~/rosjava_test_ws/src
git clone https://github.com/jubeira/rosjava_srv_test
```
- Source rosjava build tools
- Build the workspace
```
cd ~/rosjava_test_ws
cakin_make
```
### Usage
- From one terminal, start a ROS master.
- From another terminal, run a service server (source the workspace first!):
```
rosrun rosjava_bar_pkg service_test test.rosjava_bar_pkg.custom_service.ProductServer # Choose option 2 when prompted.
```
- From another terminal, run the service client (source the workspace first!):
```
rosrun rosjava_bar_pkg service_test test.rosjava_bar_pkg.custom_service.ProductClient # Choose option 2 when prompted
```
