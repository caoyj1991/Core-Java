#!/bin/sh
message="commit with out any comment"
if [ -n "$1" ];
then
message=$1
fi
echo $message
git add *
git commit -m "not need comment"
git push




