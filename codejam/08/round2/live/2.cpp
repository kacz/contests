
#include <fstream>
#include <utility>
#include <vector>
#include <iostream>
#include <iomanip>
#include <string>
#include <set>
#include <cmath>
#include <iterator>


using namespace std;


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
	for(int casenum=1;casenum<=cases;++casenum)
	{
//!!!here comes the code
		bool ready = false;
	long N, M, A;
	input >> N >> M >> A;
	double aa = A;
	long xx1,xx2,yy1,yy2;
	for(int x1=0;x1<=N;++x1)
	{
		for(long y1=0;y1<=M;++y1)
		{
			for(long x2=0;x2<=N;++x2)
			{
				for(long y2=0;y2<=M;++y2)
				{
					if(x2*y1-x1*y2 == aa)
					{
						ready = true;
						xx1=x1;
						xx2=x2;
						yy1=y1;
						yy2=y2;
					}
					if(ready) break;
				}
				if(ready) break;

			}
			if(ready) break;
		}
		if(ready) break;
	}
	if(ready)
	{
		output << "Case #" << casenum << ": " <<"0 0 " << xx1 << " " << yy1 << " " << xx2 << " " << yy2 <<endl;
	}else{
		output << "Case #" << casenum << ": " << "IMPOSSIBLE" <<endl;
	};
//!!!
	//output << "Case #" << casenum << ": " << /* the result*/ <<endl;
	}
	return 0;
}

