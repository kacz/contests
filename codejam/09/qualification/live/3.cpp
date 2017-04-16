
#include <QtCore/QCoreApplication>
#include <QTextStream>
#include <QStringList>
#include <QRegExp>

int comp(QString& welcome,QString& line,int wi,int li)
{
	int res = 0;
	if(wi == 19) return 1;	//pattern found
	if(li == line.size()) return 0;
	if(welcome.at(wi) == line.at(li)) res+=comp(welcome,line,wi+1,li+1);
	res += comp(welcome,line,wi,li+1);
	return res % 10000;
}

int main(int argc, char *argv[])
{
	int cases;
	QTextStream input(stdin);
	QTextStream output(stdout);
	input >> cases;
	QString welcome = "welcome to code jam";
	QString line;
	QString zeros;
	input.readLine();
	for(int i = 0; i < cases; ++i)
	{
		line = input.readLine();
		int match = 0;
		int result = comp(welcome,line,0,0);
		if(result / 10 == 0) zeros = "000";
		if(result / 10 > 0) zeros = "00";
		if(result / 10 > 10) zeros = "0";
		if(result / 10 > 100) zeros = "";
		output << "Case #" << i << ": "  << zeros << result << endl;
	}

	return 0;
}


