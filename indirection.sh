lein deps 
lein compile
for ((i=1;i<=1;i++))
do
    lawnsize=( 8 10 12 )
    runsize=( 100 125 150 )
    for ((j=0;j<3;j++))
    do
	echo "lawnmower 8x${lawnsize[j]} without indirection"
	lein run examples.lawnmower --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45
	echo "lawnmower 8x${lawnsize[j]} with indirection"
	lein run examples.lawnmower --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 --use-indirection true
	bzip2 -fz output/examples.lawnmower-size-${lawnsize[j]}*
	echo "dsoar 8x${lawnsize[j]} without indirection"
	lein run examples.dsoar --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 
	echo "dsoar 8x${lawnsize[j]} with indirection"
	lein run examples.dsoar --size ${lawnsize[j]} --limit ${runsize[j]} --mutation-probability 0.45 --crossover-probability 0.45 --use-indirection true
	bzip2 -fz output/examples.dsoar-size-${lawnsize[j]}*
    done
done