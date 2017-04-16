// yapocet.cpp : Defines the entry point for the console application.
//

#include <fstream>
#include <utility>
//#include <vector>
#include <iostream>
#include <iomanip>
#include <string>
//#include <set>
#include <list>
using namespace std;

struct trip
{
	int dep;
	int arr;
	char from;
	bool used;
};

int comp(trip* t1, trip* t2)
{
	if(t1->dep < t2->dep) return 1;
	return 0;
}

int main(int argc, char * argv[])
{
	int cases;
	ifstream input;
	input.open( "in" );
	if(!(input.is_open())){
		cerr << input.is_open();
		return 1;
	};
	ofstream output;
	output.open( "out", ios_base::out);
	if(!(output.is_open())){
		cerr << output.is_open();
		return 1;
	};
	input >> cases;
	input >> ws;
	for(int i=1;i<=cases;++i)
	{
		list<trip*> trips;
		int turntime;
		int fromA;
		int fromB;
		input >> turntime;
		input >> fromA;
		input >> fromB;
		for(int i=1;i<=fromA;++i)
		{
			string time;
			input >> time;
			int dep = (time.at(0)-'0')*600 + (time.at(1)-'0')*60 + (time.at(3)-'0')*10 + time.at(4)-'0';
			input >> time;
			int arr = (time.at(0)-'0')*600 + (time.at(1)-'0')*60 + (time.at(3)-'0')*10 + time.at(4)-'0' + turntime;
			trip* thisTrip = new trip();
			thisTrip->arr = arr;
			thisTrip->dep = dep;
			thisTrip->from = 'A';
			thisTrip->used = false;
			trips.push_back(thisTrip);
		}
		for(int i=1;i<=fromB;++i)
		{
			string time;
			input >> time;
			int dep = (time.at(0)-'0')*600 + (time.at(1)-'0')*60 + (time.at(3)-'0')*10 + time.at(4)-'0';
			input >> time;
			int arr = (time.at(0)-'0')*600 + (time.at(1)-'0')*60 + (time.at(3)-'0')*10 + time.at(4)-'0' + turntime;
			trip* thisTrip = new trip();
			thisTrip->arr = arr;
			thisTrip->dep = dep;
			thisTrip->from = 'B';
			thisTrip->used = false;
			trips.push_back(thisTrip);
		}
		trips.sort(comp);
		bool ready = false;
		int solA = 0;
		int solB = 0;
		while(!ready)
		{
			ready = true;
			int currtime=0;
			char currplace = 'C';
			for each (trip* elem in trips)
			{
				if((currplace == 'C') && (elem->used == false))
				{
					ready = false;
					elem->used = true;
					currtime = elem->arr;
					currplace=(elem->from=='A'?'B':'A');
					//output << "csoke1" << endl;
					if(elem->from=='A')
					{
						++solA;
					}else{
						++solB;
					};
				}
				if(elem->dep >= currtime && elem->used == false && elem->from == currplace)
				{
					ready = false;
					elem->used = true;
					currtime = elem->arr;
					currplace=(elem->from=='A'?'B':'A');
					//output << "csoke2" << endl;
				}
			}
		}
		output << "Case #" << i << ": " << solA << " " << solB << endl;
	}
	return 0;
}

