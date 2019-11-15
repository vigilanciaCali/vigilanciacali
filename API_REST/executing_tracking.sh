#!/bin/sh

#sh executing_tracking.sh path_in 4 6 34 23 234 path_out
matlab_exec=matlab
X="${1}('${2}','${3}',${4},${5},${6},${7},${8},'${9}','${10}')"
echo ${X} > matlab_command.m
cat matlab_command.m
${matlab_exec} -nodisplay -nosplash < matlab_command.m
rm matlab_command.m

