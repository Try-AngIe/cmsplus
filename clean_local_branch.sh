#!/bin/bash

for i in */.git; do
echo
echo $i '--------------------------------'
cd $i/..
git status
git fetch --all -p
git pull --all
GONE_BRANCHES=`git branch -vv | grep gone | sed | awk '{print $1}' | tr '\n' ' '`
if [[ ${#GONE_BRANCHES} -gt 2 ]]; then
    git branch -d $GONE_BRANCHES
fi
echo
git branch -a | cat
cd ..
done