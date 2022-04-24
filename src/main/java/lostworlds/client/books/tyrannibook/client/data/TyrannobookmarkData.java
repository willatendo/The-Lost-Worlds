package lostworlds.client.books.tyrannibook.client.data;

public class TyrannobookmarkData 
{
	public String text;
	public int color = 0x464646;
	public String page;

	public TyrannobookmarkData() 
	{
		this("");
	}

	public TyrannobookmarkData(String page) 
	{
		this(page, "");
	}

	public TyrannobookmarkData(String page, String text) 
	{
		this.page = page;
		this.text = text;
	}
}
