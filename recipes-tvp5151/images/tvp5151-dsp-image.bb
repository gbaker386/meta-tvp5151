# Add DSP and gstreamer packages to console image

require tvp5151-console-image.bb

DSP_INSTALL += " \
    gstreamer-ti \
    gst-plugins-good-meta \
    gst-plugins-bad-meta \
 "

IMAGE_INSTALL += " \
    ${DSP_INSTALL} \
 "

export IMAGE_BASENAME = "tvp5151-dsp-image"

