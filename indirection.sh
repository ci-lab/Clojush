rm -r test_cases
echo 'rm test_cases'
mkdir test_cases
echo 'mkdir test_cases'
mkdir test_cases/direct_only
echo 'mkdir test_cases/direct_only'
mkdir test_cases/with_indirection
echo 'mkdir test_cases/with_indirection'
for ((i=1;i<=1;i++))
do
    lawnsize=( 8 10 12 )
    runsize=( 100 125 150 )
    for ((j=0;j<5;j++))
    do
	lein run -m examples.lawnmower --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 1> test_cases/direct_only/lawnmower8x${lawnsize[j]}-${runsize[j]}-$i.out 2> test_cases/direct_only/lawnmower8x${lawnsize[j]}-${runsize[j]}-$i.err
	echo 	'lein run -m examples.lawnmower --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 1> test_cases/direct_only/lawnmower8x${lawnsize[j]}-${runsize[j]}-$i.out 2> test_cases/direct_only/lawnmower8x${lawnsize[j]}-${runsize[j]}-$i.err'
	lein run -m examples.lawnmower --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 --use-indirection true 1> test_cases/with_indirection/lawnmower8x${lawnsize[j]}-${runsize[j]}-$i.out 2> test_cases/with_indirection/lawnmower8x${lawnsize[j]}-${runsize[j]}-$i.err
	echo	'lein run -m examples.lawnmower --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 --use-indirection true 1> test_cases/with_indirection/lawnmower8x${lawnsize[j]}-${runsize[j]}-$i.out 2> test_cases/with_indirection/lawnmower8x${lawnsize[j]}-${runsize[j]}-$i.err'
	lein run -m examples.dsoar --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 1> test_cases/dsoar8x${lawnsize[j]}-${runsize[j]}-$i.out 2> test_cases/direct_only/dsoar8x${lawnsize[j]}-${runsize[j]}-$i.err
	echo	'lein run -m examples.dsoar --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 1> test_cases/dsoar8x${lawnsize[j]}-${runsize[j]}-$i.out 2> test_cases/direct_only/dsoar8x${lawnsize[j]}-${runsize[j]}-$i.err'
	lein run -m examples.dsoar --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 --use-indirection true 1> test_cases/with_indirection/dsoar8x${lawnsize[j]}-${runsize[j]}-$i.out 2> test_cases/with_indirection/dsoar8x${lawnsize[j]}-${runsize[j]}-$i.err
	echo    'lein run -m examples.dsoar --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 --use-indirection true 1> test_cases/with_indirection/dsoar8x${lawnsize[j]}-${runsize[j]}-$i.out 2> test_cases/with_indirection/dsoar8x${lawnsize[j]}-${runsize[j]}-$i.err'
    done
done