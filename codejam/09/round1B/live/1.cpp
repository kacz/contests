
#include <QtCore/QCoreApplication>
#include <QTextStream>
#include <QStringList>
#include <QRegExp>

class node
{
public:
	QString feature;
	double weight;
	node* left;
	node* right;
};


node* addnode(QTextStream& input)
{
	QChar c;
	input >> c;
	bool hasfeature = false;
	node* tree = new node();
	tree->left = 0;
	tree->right = 0;
	bool finish = false;
	tree->feature = "";
	while(c != ')')
	{
		char cx = c.toAscii();
		if(c == '(') input >> tree->weight;
		if(c.isLetter()) { tree->feature.append(c); hasfeature = true;}
		if((c == ' ' || c == '\n') && hasfeature && !finish) 
		{
			tree->left = addnode(input);
			tree->right = addnode(input);
			finish = true;
		}
		input >> c;
	}
	return tree;
}

int main(int argc, char *argv[])
{
	int cases;

	QTextStream input(stdin);
	QTextStream output(stdout);
	output.setRealNumberNotation(QTextStream::FixedNotation);
	output.setRealNumberPrecision(7);
	
	input >> cases;

	for(int i=1;i<=cases;++i)
	{
		node* tree = 0;
		int lines;
		input >> lines;

		if(lines) tree = addnode(input);

		int animals;
		input >> animals;
		output << "Case #" << i << ": " << endl;
		for(int j = 0; j < animals; ++j)
		{
			QString name;
			input >> name;
			QString feature;
			QStringList features;
			int fea;
			input >> fea;
			for(int f= 0; f < fea; ++f)
			{
				input >> feature;
				features.append(feature);
			}
			node* a = tree;
			double w = 1;
			while(a){
				w *= a->weight;
				if(features.contains(a->feature)){
					a=a->left;
				}else{
					a=a->right;
				}
			}
			output << w << endl;
		}
	}

	return 0;
}
