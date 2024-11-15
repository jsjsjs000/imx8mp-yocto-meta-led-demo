SUMMARY = "LED demo recipe"
DESCRIPTION = "LED demo recipe - Add files"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/led-demo:"
SRC_URI = " \
	file://imx8mp_a53_led_demo.bin \
	file://imx8mp_qt_led_demo \
	file://imx8mp_kernel_driver_led_demo.conf \
	file://imx8mp_qt_led_demo.service \
"

DEPENDS += " i2c-tools qtbase qtdeclarative"
#DEPENDS += " libi2c"
#RDEPENDS_${PN} += " libi2c.so()(64bit)"
#DEPENDS_append = " python dtc-native bison-native"

inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "imx8mp_qt_led_demo.service"
# Create symlink /etc/systemd/system/multi-user.target.wants/imx8mp_qt_led_demo.service -> /lib/systemd/system/imx8mp_qt_led_demo.service

do_install() {
	# touch file.txt
	install -d ${D}/root
	install -m 0755 ${WORKDIR}/imx8mp_a53_led_demo.bin ${D}/root

	install -d ${D}/usr/share/imx8mp_qt_led_demo
	install -m 0755 ${WORKDIR}/imx8mp_qt_led_demo ${D}/usr/share/imx8mp_qt_led_demo
	install -d ${D}/usr/bin
	ln ${D}/usr/share/imx8mp_qt_led_demo/imx8mp_qt_led_demo ${D}/usr/bin/imx8mp_qt_led_demo
	ln ${D}/usr/share/imx8mp_qt_led_demo/imx8mp_qt_led_demo ${D}/root/imx8mp_qt_led_demo

	install -d ${D}/etc/modules-load.d
	install -m 0644 ${WORKDIR}/imx8mp_kernel_driver_led_demo.conf ${D}/etc/modules-load.d

	install -d ${D}/lib/systemd/system
	install -m 0644 ${WORKDIR}/imx8mp_qt_led_demo.service ${D}/${systemd_unitdir}/system
}

FILES_${PN} += "/root/imx8mp_a53_led_demo.bin"
FILES_${PN} += "/usr/share/imx8mp_qt_led_demo/imx8mp_qt_led_demo"
FILES_${PN} += "/usr/bin/imx8mp_qt_led_demo"
FILES_${PN} += "/root/imx8mp_qt_led_demo"
FILES_${PN} += "/etc/modules-load.d/imx8mp_kernel_driver_led_demo.conf"
FILES_${PN} += "${systemd_unitdir}/system/imx8mp_qt_led_demo.service"
