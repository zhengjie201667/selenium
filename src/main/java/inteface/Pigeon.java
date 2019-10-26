package inteface;

public class Pigeon extends Bird implements Flyanimal{

	@Override
	public void fly() {
		// TODO Auto-generated method stub
	       System.out.println("pigeon  can fly");
	}
    public void egg(){
       System.out.println("pigeon  can lay  eggs ");
    }
}
