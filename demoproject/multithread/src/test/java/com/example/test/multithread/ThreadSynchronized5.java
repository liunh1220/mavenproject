package com.example.test.multithread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger
 * @author Administrator
 *
 */
public class ThreadSynchronized5 {

	class Bank {

		private AtomicInteger account = new AtomicInteger(10);

		public AtomicInteger getAccount() {
			return account;
		}

		public void save(int money) {
			account.addAndGet(money);
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

		ThreadSynchronized5 st = new ThreadSynchronized5();
		st.useThread();
	}

}
