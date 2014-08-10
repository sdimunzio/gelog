package org.hamradio.lw4hbr;

import java.util.List;

public class ChartUtils {

	
	public static String getImageChart(List<String> values, List<String> labels){
		
		StringBuffer t=new StringBuffer();
		StringBuffer chxl= new StringBuffer();
		
		for(int i=0; i < values.size();i++){
			t.append(values.get(i));
			t.append(",");
			chxl.append(labels.get(i));
			chxl.append("|");
		}
		t.deleteCharAt(t.length()-1);
		chxl.deleteCharAt(chxl.length()-1);
		
		String imageUrl="http://chart.apis.google.com/chart?" +
				"chxl=1:" +chxl.toString()+
				"&chxr=0,-5,100"+
				"&chxt=y,x" +
				"&chbh=a" +
				"&chs=300x225" +
				"&cht=bvs" +
				"&chd=t:" +t.toString()+
				"&chdlp=l&chtt=Rate";

		return imageUrl;
	}
}
