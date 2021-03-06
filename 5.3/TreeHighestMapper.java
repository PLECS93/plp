package cs.bigdata.Tutorial2;

import org.apache.hadoop.mapreduce.Job;  
import org.apache.hadoop.io.*;        
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;


public class TreeHighestMapper extends Mapper<IntWritable, Text, Text, IntWritable> {

	//private Text word = new Text();

	protected void map(Text keyE, IntWritable valE, Context context) throws IOException,InterruptedException
    {
		String content = keyE.toString();
		String[] lines = content.split("\n");

		for(String line: lines )
		{
			String[] splitted = line.split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			
			// Extract the tree's height
			String height = splitted[6];
			
			if(height == null | height.length() == 0) {
				height = " NA ";
			}
			
			Text keyS = new Text(height);
			IntWritable valS = new IntWritable(1);
			context.write(keyS, valS);
		}
    }
}
