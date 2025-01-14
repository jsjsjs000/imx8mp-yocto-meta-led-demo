require recipes-images/images/phytec-headless-image.bb

SUMMARY = "LED demo image - based on phytec-headless-image"

IMAGE_FEATURES += "\
    splash \
    ssh-server-openssh \
    hwcodecs \
    qtcreator-debug \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston', '', d)} \
"

LICENSE = "MIT"

inherit populate_sdk_qt5

IMAGE_INSTALL += "\
    packagegroup-base \
    \
    packagegroup-gstreamer \
    \
    qt5-opengles2-test \
    phytec-qtdemo \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland qtwayland-plugins weston weston-init', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland', '', d)} \
    led-demo \
"

IMAGE_INSTALL_remove_mx6ul = "\
    qt5-opengles2-test \
"

IMAGE_INSTALL_remove_mx7d = "\
    qt5-opengles2-test \
"
