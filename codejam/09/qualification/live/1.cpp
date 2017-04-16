
#include <QtCore/QCoreApplication>
#include <QTextStream>
#include <QStringList>
#include <QRegExp>
int main(int argc, char *argv[])
{
	int cases;
	int l;
	int d;
	QTextStream input(stdin);
	QTextStream output(stdout);
	QStringList words;
	input >> l >> d >> cases;
	QString word;
	for(int i = 0; i < d; ++i)
	{
		input >> word;
		words.append(word);
	}
	QString pattern;
	QStringList result;
	for(int i=1;i<=cases;++i)
	{
		input >> pattern;
		pattern.replace('(',"[");
		pattern.replace(')',"]");
		result = words.filter(QRegExp(pattern));
		output << "Case #" << i << ": " << result.size() << endl;
	}

	return 0;
}
