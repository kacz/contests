
#include <QtCore/QCoreApplication>
#include <QList>
#include <QTextStream>

int main(int argc, char *argv[])
{
	QTextStream in(stdin);
	QTextStream out(stdout);
	int cases;
	in >> cases;
	for(int i = 0; i<cases;++i)
	{
		QList<long> pos;
		QList<int> speed;
		int result = 0;
		long n,k,b,t;
		in >> n >> k >> b >> t; 
		for(int j = 0; j < n;++j)
		{
			int tmp;
			in >> tmp;
			pos.append(tmp);
		}
		for(int j = 0; j < n;++j)
		{
			int tmp;
			in >> tmp;
			speed.append(tmp);
		}

		int lassu = 0;
		int gyors = 0;
		for(int j = n-1; j>=0; --j)
		{
			if(b-pos.at(j)<=t*speed.at(j)) // ez beer
			{
				result+=lassu;
				gyors++;
				if(gyors>=k) break;
			}else{
				lassu++;
			}
		}
		if(gyors<k)
		{
			out << "Case #" << i+1 << ": " << "IMPOSSIBLE" << endl;
		}else{
			out << "Case #" << i+1 << ": " << result << endl;
		}
	}
	return 0;
}

