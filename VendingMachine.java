public class VendingMachine {
	private int quarters;
	private int nickels;
	private int dimes;
	private int value;
	private boolean change;

	public VendingMachine(int q, int d, int n, int v, boolean c) {
		quarters = q;
		dimes = d;
		nickels = n;
		value = v;
		change = c;
	}

	public String lambda() throws NoChangeException {
		String ret = "";

		if(change) {
			ret += getChange(value % 100);
		}

		for(int i = 0; i < value / 100; i++) {
			ret += "<coffee>";
		}

		if(ret.compareTo("") == 0) {
			ret = "nothing";
		}

		return ret;
	}

	public void delta(String input) throws NoChangeException {
		value = value % 100; // Reset after coffee withdrawal
		if(change) {
			for (char i : getChange(value).toCharArray()) {
				if(i == 'q') {
					quarters--;
					value -= 25;
				}
				else if(i == 'd') {
					dimes--;
					value -= 10;
				}
				else if(i == 'n') {
					nickels--;
					value -= 5;
				}
			}
		}

		change = false;

		int q = 0;
		int d = 0;
		int n = 0;

		for (char i : input.toCharArray()) {
			if(i == 'q') {
				q++;
			}
			else if(i == 'd') {
				d++;
			}
			else if(i == 'n') {
				n++;
			}
			else if(i == 'c') {
				change = true;
			}
		}

		quarters += q;
		dimes += d;
		nickels += n;

		value += q*25 + d*10 + n*5;
	}

	private String getChange(int v) throws NoChangeException {
		String ret = "";

		int q = v / 25 > quarters ? quarters : v / 25;
		int d = (v-q*25) / 10 > dimes ? dimes : (v-q*25) / 10;
		int n = ((v-q*25)-d*10) / 5 > nickels ? nickels : ((v-q*25)-d*10) / 5;

		if(q*25 + d*10 + n*5 != v) {
			throw new NoChangeException("Not enough change in machine");
		}

		for(int i = 0; i < q; i++) {
			ret += "q";
		}
		for(int i = 0; i < d; i++) {
			ret += "d";
		}
		for(int i = 0; i < n; i++) {
			ret += "n";
		}

		return ret;
	}
}
