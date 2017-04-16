
#include <QtCore/QCoreApplication>
#include <QList>
#include <QTextStream>


long minim(int index, int heap[], int hany,int p)
{
	int root = (1 << (p+1)) -2; 
	if(index < (1 << p))
	{
		if(hany < p-heap[index]) return -1;
		return 0;
	}
	long res, res2;
	long elso = minim(-root +2*index-1,heap,hany+1,p);
	long masodik = minim(-root +2*index-2,heap,hany+1,p);
	if(elso >= 0 && masodik >= 0)
		res = elso + masodik + heap[index];
	else res = -1;
	elso = minim(-root +2*index-1,heap,hany,p);
	masodik = minim(-root +2*index-2,heap,hany,p);
	if(elso >= 0 && masodik >= 0)
		res2 = elso + masodik;
	else res2 = -1;

	if(res<0) return res2;
	if(res2<0) return res;

	return (res < res2 ? res : res2);
}
int main(int argc, char *argv[])
{
	QTextStream in(stdin);
	QTextStream out(stdout);
	int cases;
	in >> cases;
	for(int i = 0; i<cases;++i)
	{
		int heap[2048];// = new int[2048];//(int[])malloc(2048*sizeof(int));
		for(int j = 0; j < 2048;++j) heap[j] = 0;

		int index = 0;
		int p;
		in >> p;
		for(int j = 0; j < (1<<(p)); ++j)
			in >> heap[index++];
		for(int j = 0; j < (1<<(p))-1 ; ++j)
			in >> heap[index++];
		index--;

		long result = minim(index,heap,0,p);


		out << "Case #" << i+1 << ": " << result << endl;
	}
	return 0;
}

