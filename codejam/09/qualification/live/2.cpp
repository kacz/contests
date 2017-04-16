
#include <QtCore/QCoreApplication>
#include <QTextStream>
#include <QStringList>
#include <QRegExp>
#include <QList>
#include <QMap>


struct elem
{
	int alt;
	int label;
};

class Pos
{
public:
	Pos(int _x,int _y):x(_x),y(_y){};
	int x;
	int y;
	bool operator==(Pos p) {if(x == p.x && y == p.y) return true; return false;}
};

bool isSink(QList<QList<elem>>& map,int x, int y)
{
	int h = map.size();
	int w = map.at(0).size();
	int alt = map.at(y).at(x).alt;
	if(x>0 && map.at(y).at(x-1).alt < alt) return false;
	if(y>0 && map.at(y-1).at(x).alt < alt) return false;
	if(x<w-1 && map.at(y).at(x+1).alt < alt) return false;
	if(y<h-1 && map.at(y+1).at(x).alt < alt) return false;
	return true;
	
}

Pos flowsTo(QList<QList<elem>>& map,int x, int y)
{
	int h = map.size();
	int w = map.at(0).size();
	if(x<0 || y<0 || x >w-1 || y>h-1) return Pos(-1,-1);
	int alt = map.at(y).at(x).alt;
	int altN = -1;
	int altW = -1;
	int altS = -1;
	int altE = -1;
	if(x>0) altW = map.at(y).at(x-1).alt;
	if(y>0) altN = map.at(y-1).at(x).alt;
	if(y<h-1) altS = map.at(y+1).at(x).alt;
	if(x<w-1) altE = map.at(y).at(x+1).alt;
	int min = alt;
	if(altW < min && altW > -1) min = altW;
	if(altN < min && altN > -1) min = altN;
	if(altS < min && altS > -1) min = altS;
	if(altE < min && altE > -1) min = altE;
	if(min == alt) return Pos(x,y);
	if(min == altN) return Pos(x,y-1);
	if(min == altW) return Pos(x-1,y);
	if(min == altE) return Pos(x+1,y);
	if(min == altS) return Pos(x,y+1);
	return Pos(-1,-1);
}


int main(int argc, char *argv[])
{
	int cases;
	QTextStream input(stdin);
	QTextStream output(stdout);
	input >> cases;
	for(int i = 0; i < cases; ++i)
	{
		int h;
		int w;
		input >> h;
		input >> w;
		QList<QList<elem>> map;
		for(int j = 0; j < h; ++j)
		{
			QList<elem> line;
			for(int k = 0; k < w; ++k)
			{
				elem e;
				input >> e.alt;
				e.label = -1;
				line.append(e);
			}
			map.append(line);
		}
		int label = 0;
		QList<Pos> sinks;
		for(int y = 0; y < h; ++y)
			for(int x = 0; x < w; ++x){
				if(isSink(map,x,y)) sinks.append(Pos(x,y));
				map[y][x].label = label++;
			}


		foreach(Pos s,sinks){
			QList<Pos> elems;
			if(s.x>0 && flowsTo(map,s.x-1,s.y) == s){
				map[s.y][s.x-1].label = map.at(s.y).at(s.x).label;
				elems.append(Pos(s.x-1,s.y));
			}
			if(s.y>0 && flowsTo(map,s.x,s.y-1) == s){
				map[s.y-1][s.x].label = map.at(s.y).at(s.x).label;
				elems.append(Pos(s.x,s.y-1));
			}
			if(s.x<w-1 && flowsTo(map,s.x+1,s.y) == s){
				map[s.y][s.x+1].label = map.at(s.y).at(s.x).label;
				elems.append(Pos(s.x+1,s.y));
			}
			if(s.y<h-1 && flowsTo(map,s.x,s.y+1) == s){
				map[s.y+1][s.x].label = map.at(s.y).at(s.x).label;
				elems.append(Pos(s.x,s.y+1));
			}
			while(!elems.isEmpty())
			{
				Pos el = elems.takeFirst();
				if(el.x>0 && flowsTo(map,el.x-1,el.y) == el){
					map[el.y][el.x-1].label = map.at(el.y).at(el.x).label;
					elems.append(Pos(el.x-1,el.y));
				}
				if(el.y>0 && flowsTo(map,el.x,el.y-1) == el){
					map[el.y-1][el.x].label = map.at(el.y).at(el.x).label;
					elems.append(Pos(el.x,el.y-1));
				}
				if(el.x<w-1 && flowsTo(map,el.x+1,el.y) == el){
					map[el.y][el.x+1].label = map.at(el.y).at(el.x).label;
					elems.append(Pos(el.x+1,el.y));
				}
				if(el.y<h-1 && flowsTo(map,el.x,el.y+1) == el){
					map[el.y+1][el.x].label = map.at(el.y).at(el.x).label;
					elems.append(Pos(el.x,el.y+1));
				}
			}

		}
		output << "Case #" << i+1 << ":" << endl;
		QMap<int,char> letters;
		char curr = 'a';
		for(int y = 0; y < h; ++y)
		{
			for(int x = 0; x < w; ++x)
			{
				//output << map.at(y).at(x).label << " ";
				
				if(!letters.contains(map.at(y).at(x).label)) letters.insert(map.at(y).at(x).label,curr++);
				output << letters.value(map.at(y).at(x).label) << " ";
				
			}
			output << endl;
		}
	}

	return 0;
}


