
#include <QtCore/QCoreApplication>
#include <QTextStream>
#include <QStringList>
#include <QRegExp>
int main(int argc, char *argv[])
{
	int cases;

	QTextStream input(stdin);
	QTextStream output(stdout);
	
	input >> cases;

	for(int i=1;i<=cases;++i)
	{
		QString number;
		QString maradek;
		QString result;
		input >> number;
		maradek.append(number.at(number.length()-1));
		bool vege = false;
		for(int j = number.length()-2; j > -1; --j)
		{
			if(number.at(j).toAscii()<number.at(j+1).toAscii())
			{
				maradek.append(number.at(j));
				for(int k = 0; k < j; ++k)
					result.append(number.at(k));
				int mar[10];
				for(int l = 0; l < 10; ++l) mar[l] = 0;
				for(int l = 0; l < maradek.size(); ++l) mar[maradek.at(l).toAscii()-'0']++;
				for(char l = number.at(j).toAscii() - '0' + 1; l < 10; ++l) if(mar[l]){result.append(QString::number(l)); mar[l]--; break;}
			//	result.append(number.at(j));
				for(int l = 0; l < 10; ++l) for(int p = 0; p < mar[l]; ++p) result.append(QString::number(l));
				vege = true;
				break;
			}
			maradek.append(number.at(j));
		}
		if(vege)
			output << "Case #" << i << ": " << result << endl;
		else{
			bool elso = true;
			int mar[10];
			for(int l = 0; l < 10; ++l) mar[l] = 0;
			for(int l = 0; l < maradek.size(); ++l) mar[maradek.at(l).toAscii()-'0']++;
			for(int l = 1; l < 10; ++l) if(mar[l]){result.append(QString::number(l)); mar[l]--; break;}
			result.append('0');
			elso = false;
			for(int l = 0; l < 10; ++l) for(int p = 0; p < mar[l]; ++p) {result.append(QString::number(l)); if(elso) result.append('0');}
			output << "Case #" << i << ": " << result << endl;

		}
	}

	return 0;
}
