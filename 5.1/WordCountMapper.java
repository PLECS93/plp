package cs.bigdata.Lab2;

import org.apache.hadoop.mapreduce.Job;  
import org.apache.hadoop.io.*;        
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text word = new Text();

	protected void map(Text keyE, IntWritable valE, Context context) throws IOException,InterruptedException
    {
		String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
		
		String line = keyE.toString();
		String[] words=line.split(" ");
		
		for(String word: words )
		{
			if(word.length() > 3)
			{
			  // add the docName as docId
			  word = word + "$" + fileName;
			  
			  Text keyS = new Text(word);
					  
			  IntWritable valS = new IntWritable(1);
			  context.write(keyS, valS);
			}
		}
    }
}
