#!/bin/bash
#
# (c) Copyright 2012 Scott Ellis <scott@pansenti.com>
# Licensed under terms of GPLv2
#
# Based in large on the mksdcard.sh script from Steve Sakoman
# http://www.sakoman.com/category/3-bootable-sd-microsd-card-creation-script.html
#

if [ -n "$1" ]; then
	DRIVE=/dev/$1
else
	echo -e "\nUsage: sudo $0 <device>\n"
	echo -e "Example: sudo $0 sdb\n"
	exit 1
fi

if [ "$DRIVE" = "/dev/sda" ] ; then
	echo "Sorry, not going to format $DRIVE"
	exit 1
fi


echo -e "\nWorking on $DRIVE\n"

# unmount drive partitions before we start
for i in 1 2 3 4 5 6 7 8 9
do
    if [ -b ${DRIVE}${i} ]; then
        umount ${DRIVE}${i}
    fi
done

SIZE=`fdisk -l $DRIVE | grep "Disk $DRIVE" | cut -d' ' -f5`

CYLINDERS=`echo $SIZE/255/63/512 | bc`

echo CYLINDERS – $CYLINDERS

echo -e "\nOkay, here we go ...\n"

echo -e "=== Zeroing the MBR ===\n"
dd if=/dev/zero of=$DRIVE bs=1024 count=1024

# Create 1 Linux partition
# Sectors are 512 bytes
# MBR goes in first sector
# Next 127 sectors are empty to align first partition on a 128 sector boundary

echo -e "\n=== Creating 1 partition ===\n"
{
echo 128,,,-
} | sfdisk --force -D -uS -H 255 -S 63 -C $CYLINDERS $DRIVE


sleep 1


echo -e "\n=== Formatting the partion as ext3 ===\n"
mkfs.ext3 ${DRIVE}1

echo -e "\n=== Done! ===\n"

