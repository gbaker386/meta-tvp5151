# Add DSP and gstreamer packages to console image

require tvp5151-console-image.bb

PR = "1"

DSP_INSTALL += " \
    gstreamer-ti \
    gst-plugins-base-meta \
    gst-plugins-good-meta \
    gst-plugins-bad-meta \
 "

QT_TOOLS = " \
    qt4-embedded-dev\
    qt4-embedded \
 "

IMAGE_INSTALL += " \
    ${DSP_INSTALL} \
    ${QT_TOOLS} \
 "

export IMAGE_BASENAME = "tvp5151-dsp-image"

