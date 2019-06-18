public class LunchOrder {
	public static class Builder {
		private String bread;
		private String condiment;
		private String dressing;
		private String meat;

		public Builder(){

		}

		public LunchOrder build(){
			return LunchOrder(this);
		}

		public Builder bread(String bread){
			this.bread = bread;
			return this;
		}
		public Builder condiment(String condiment){
			this.condiment = condiment;
			return this;
		}
		public Builder dressing(String dressing){
			this.dressing = dressing;
			return this;
		}
		public Builder meat(String meat){
			this.meat = meat;
			return this;
		}
	}

	private final String bread;
	private final String condiment;
	private final String dressing;
	private final String meat;

	private LunchOrder(Builder builder){
		this.bread = builder.bread;
		this.condiment = builder.condiment;
		this.dressing = builder.dressing;
		this.meat = builder.meat;
	}

	public String getBread(){
		return bread;
	}

	public String getCondiments(){
		return condiment;
	}

	public String getDressing(){
		return dressing;
	}

	public String getMeat(){
		return meat;
	}
}

public class BuilderLunchOrderDemo {
	public static void main(String args[]){
		LunchOrder.Builder builder = new LunchOrder.Builder();
		builder.bread("Wheat").condiment("Lettuce").dressing("Mayo").meat("Turkey");
		LunchOrder lunchOrder = new builder.build();
		System.out.println(lunchOrder.getBread());
		System.out.println(lunchOrder.getCondiments());
		System.out.println(lunchOrder.getDressing());
		System.out.println(lunchOrder.getMeat());
	}
}