package com.example.test.multithread;

/**
 * volatile实现
 * 
 * @author Administrator
 *
 */
public class ThreadSynchronized1 {

	class Bank {
		
		// 需要同步的变量加上volatile
		private volatile int account;

		public void setAccount(int account) {
			this.account = account;
		}

		public int getAccount() {
			return account;
		}

		// 这里不再需要synchronized
		public void save(int money) {
			account += money;
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
		bank.setAccount(10);

		NewThread new_thread = new NewThread(bank);

		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(new_thread);
			System.out.println("线程"+i);
			thread.start();
		}
	}

	public static void main(String[] args) {
		ThreadSynchronized1 st = new ThreadSynchronized1();
		st.useThread();
	}

}
