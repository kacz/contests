
#include <QtCore/QCoreApplication>
#include <QMap>
#include <iostream>
#include <QTextStream>

class direc
{
public:
	QMap<QString,direc> subs;
	void add(QString path) 
	{
		if(!path.length()) return;
		int ind = path.indexOf("/");
		direc* di;
		if(!subs.contains(path.mid(0,ind)))
		{
			di = new direc();
			subs.insert(path.mid(0,ind), *di);
		}
		if(ind>=0)
		{		
			di = & subs[path.mid(0,ind)];
			di->add(path.mid(ind+1));
		}
	}
	void mk(QString path, int& res)
	{
		if(!path.length()) return;
		int ind = path.indexOf("/");
		direc* di;
		if(!subs.contains(path.mid(0,ind)))
		{
			di = new direc();
			subs.insert(path.mid(0,ind), *di);
			res++;
		}
		if(ind>=0)
		{
			di = & subs[path.mid(0,ind)];
			di->mk(path.mid(ind+1),res);
		}
		
	}
};

int main(int argc, char *argv[])
{
	QTextStream in(stdin);
	QTextStream out(stdout);
	int cases;
	in >> cases;
	for(int i = 0; i<cases;++i)
	{
		direc root;
		QString path;
		int result = 0;
		int n,m;
		in >> n >> m; 
		for(int j = 0; j < n;++j)
		{
			in >> path;
			root.add(path.mid(1));
		}
		for(int j = 0; j < m;++j)
		{
			in >> path;
			root.mk(path.mid(1),result);
		}

		out << "Case #" << i+1 << ": " << result << endl;

	}
	return 0;
}

