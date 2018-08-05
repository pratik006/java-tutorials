infile="TMS.csv"
testcase="E2E"
msg="using file "
if [ -z "$1" ]
  then
    infile = "";
fi

if [ -z "$2" ]
  then
    msg="$msg$infile$2"
  else
    infile=$2
fi

if [ -z "$1" ]
  then
    msg="$msg and $testcase"
  else
    testcase=$1
fi

echo $msg
java -jar dist/auto-csv.jar $infile $testcase
