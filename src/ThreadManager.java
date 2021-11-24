
public class ThreadManager {

    public static void main(String[] args) throws InterruptedException {
        String search_thread = "";
        String search_group = "";

        long refresh_sec = 1;  // 0 means run once

        ShowThreads(search_thread, search_group, refresh_sec);  // entry
    }

    public static void ShowThreads(String search_thread, String search_group, long refresh_sec) throws InterruptedException {
        while (true) {
            ThreadManager.listThreads(search_thread, search_group);
            System.out.println("-------------------------------------");
            if (refresh_sec == 0)
                break;
            Thread.sleep(refresh_sec * 1000);
        }
    }

    public static void listThreads(String search_thread, String search_group ) {
        ThreadGroup root_group;
        ThreadGroup parent_group;

        root_group = Thread.currentThread().getThreadGroup();  // get current thread group and assume it is root
        parent_group = root_group.getParent();  // get parent of the assumed root
        while (parent_group != null) {  // loop as long as the parent is an actual group
            root_group = parent_group;  // set the parent as the new root (since it exists)
            parent_group = root_group.getParent();  // get parent of the assumed root
        }
        // at this point, the actual root is found
        printGroupInfo(root_group, search_thread, search_group);
    }

    private static void printGroupInfo(ThreadGroup group, String search_thread, String search_group) {
        if (group == null)
            return;
        int num_threads = group.activeCount();  // get number of threads in current group
        int num_groups = group.activeGroupCount();  // get number of groups linked to current group
        Thread[] threads_array = new Thread[num_threads];
        ThreadGroup[] groups_array = new ThreadGroup[num_groups];

        // populate arrays
        group.enumerate(threads_array, false);
        group.enumerate(groups_array, false);

        // print group name and contents if current group name is searched for, or if no search term is given
        if (search_group.isEmpty() || search_group.equalsIgnoreCase(group.getName())) {
            System.out.println("Thread Group: " + group.getName());
            for (Thread thread: threads_array)
                printThreadInfo(thread, search_thread);  // print threads in group
        }

        for (ThreadGroup thread_group: groups_array)
            printGroupInfo(thread_group, search_thread, search_group);  // recursive call to print the next linked group
    }

    private static void printThreadInfo(Thread t, String search_thread) {
        if (t == null)
            return;
        // print thread name and details if current thread name is searched for, or if no search term is given
        if (search_thread.isEmpty() || search_thread.equalsIgnoreCase(t.getName()))
            System.out.println("        Thread ID: " + t.getId() +  "  Name: " + t.getName() + "  Priority: " + t.getPriority()
                    + " State: " + t.getState()
                    + (t.isDaemon() ? " Daemon" : " Not Daemon"));
    }
}
