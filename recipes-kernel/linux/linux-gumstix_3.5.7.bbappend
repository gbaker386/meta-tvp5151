FILESEXTRAPATHS_prepend := "${THISDIR}/linux-gumstix-3.5:"

PRINC := "${@int(PRINC) + 1}"

SRC_URI += " \ 
	file://bt656-isp.patch \
	file://tvp5151-board-file.patch \
	file://tvp5150-media-ctl.patch \		
        "

