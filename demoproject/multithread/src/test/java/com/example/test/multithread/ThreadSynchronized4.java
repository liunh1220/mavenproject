package com.example.test.multithread;

/**
 * 注：ThreadLocal与同步机制 
        a.ThreadLocal与同步机制都是为了解决多线程中相同变量的访问冲突问题。 
        b.前者采用以"空间换时间"的方法，后者采用以"时间换空间"的方式
 * @author Administrator
 *
 */
public class ThreadSynchronized4 {

	class Bank {

		//使用ThreadLocal类管理共享变量account
        private final ThreadLocal<Integer> account = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue(){
                return 10;
            }
        };
        
        public void save(int money){
            account.set(account.get()+money);
        }
        
        public int getAccount(){
            return account.get();
        }
	}

	class NewThread implements Runnable {

		private Bank bank;

		public NewThread(Bank bank) {
			this.bank = bank;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				bank.save(1);
				System.out.println(Thread.currentThread().getName() + "," + i + "账户余额为：" + bank.getAccount());
			}
		}

	}

	/**
	 * 建立线程，调用内部类
	 */
	private void useThread() {
		Bank bank = new Bank();

		NewThread new_thread = new NewThread(bank);

		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(new_thread);
			System.out.println("线程" + i);
			thread.start();
		}
	}

	public static void main(String[] args) {
		
		ThreadSynchronized4 st = new ThreadSynchronized4();
		st.useThread();
	}

}
