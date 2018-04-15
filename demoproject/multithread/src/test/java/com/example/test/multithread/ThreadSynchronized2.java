package com.example.test.multithread;

/**
 * 同步方法、同步代码块实现
 * @author Administrator
 *
 */
public class ThreadSynchronized2 {

    class Bank {

        private int account;

        private void setAccount(int account) {
			this.account = account;
		}

		private int getAccount() {
            return account;
        }

        /**
         * 用同步方法实现
         * 
         * @param money
         */
        private synchronized void saveMethod(int money) {
            account += money;
        }

        /**
         * 用同步代码块实现
         * 
         * @param money
         */
        private void saveCodeBlock(int money) {
            synchronized (this) {
                account += money;
            }
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
                //bank.saveCodeBlock(1);
                bank.saveMethod(1);
                System.out.println(Thread.currentThread().getName()+","+i + "账户余额为：" + bank.getAccount());
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
    	ThreadSynchronized2 st = new ThreadSynchronized2();
        st.useThread();
    }

}
