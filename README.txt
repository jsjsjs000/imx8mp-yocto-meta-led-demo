# copy folder to: ~/phyLinux/sources/

# set Yocto environment
cd ~/phyLinux
source sources/poky/oe-init-build-env

# prepare SD card image
bitbake led-demo-image
