# A console development image with some C/C++ dev tools

require tvp5151-boot-image.bb

CORE_OS = " \
    avahi \
    busybox-hwclock \
    ntp \
    ntp-tickadj \
    task-core-ssh-openssh openssh-keygen openssh-sftp-server \
    term-prompt \
    tzdata \
 "

# Custom kernel modules built out of tree

KERNEL_MODULES_OOT = " \
    omap3-pwm \
    omap3-mux \
    omap3-irqlat \
    hrt-test \
    udelay-test \
 "

KERNEL_EXTRA_INSTALL = " \
    kernel-modules \
    ${KERNEL_MODULES_OOT} \
 "

WIFI_SUPPORT = " \
    linux-firmware-sd8686 \
    linux-firmware-rtl8192ce \
    linux-firmware-rtl8192cu \
    linux-firmware-wl12xx \
    wpa-supplicant \
 "

DEV_SDK_INSTALL = " \
    binutils \
    binutils-symlinks \
    coreutils \
    cpp \
    cpp-symlinks \
    diffutils \
    file \
    gcc \
    gcc-symlinks \
    g++ \
    g++-symlinks \
    gettext \
    git \
    ldd \
    libstdc++ \
    libstdc++-dev \
    libtool \
    make \
    pkgconfig \
 "

EXTRA_TOOLS_INSTALL = " \
    bzip2 \
    ethtool \
    findutils \
    i2c-tools \
    iftop \
    iperf \
    htop \
    less \
    nano \
    sysfsutils \
    tcpdump \
    unzip \
    wget \
    zip \
 "

V4L_TOOLS = " \
    media-ctl \
    v4l-utils \
    yavta \
 "

ALSA_TOOLS = " \
    alsa-dev \
    alsa-lib \
    alsa-lib-dev \
 "

MISC_EXTRA = " \
    polladc \
 "

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${DEV_SDK_INSTALL} \
    ${EXTRA_TOOLS_INSTALL} \
    ${KERNEL_EXTRA_INSTALL} \
    ${ALSA_TOOLS} \
    ${MISC_EXTRA} \
    ${WIFI_SUPPORT} \
 "

set_local_timezone() {
    ln -sf /usr/share/zoneinfo/EST5EDT ${IMAGE_ROOTFS}/etc/localtime
}

IMAGE_FILE_BLACKLIST += " \
    /etc/init.d/hwclock.sh \
 "

remove_blacklist_files() {
    for i in ${IMAGE_FILE_BLACKLIST}; do
        rm -rf ${IMAGE_ROOTFS}$i
    done
}

ROOTFS_POSTPROCESS_COMMAND += "set_local_timezone ; "

export IMAGE_BASENAME = "tvp5151-console-image"


