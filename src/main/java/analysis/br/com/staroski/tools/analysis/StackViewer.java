package br.com.staroski.tools.analysis;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;

final class StackViewer {

    private class ThreadPanel extends JPanel {

        private class StackModel extends AbstractTableModel {

            private static final long serialVersionUID = 1;

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int col) {
                switch (col) {
                    case 0:
                        return "Source File Name";
                    case 1:
                        return "Source File Line";
                    case 2:
                        return "Class Name";
                    case 3:
                        return "Method Name";
                }
                return null;
            }

            @Override
            public int getRowCount() {
                synchronized (LOCK) {
                    return stack != null ? stack.length : 0;
                }
            }

            @Override
            public Object getValueAt(int row, int col) {
                synchronized (LOCK) {
                    if (row < stack.length) {
                        switch (col) {
                            case 0:
                                String name = stack[row].getFileName();
                                return name == null || name.isEmpty() ? "unknown" : name;
                            case 1:
                                int number = stack[row].getLineNumber();
                                return number > -1 ? number : "unknown";
                            case 2:
                                return stack[row].getClassName();
                            case 3:
                                return stack[row].getMethodName();

                        }
                    }
                    return null;
                }
            }

        }

        private static final long serialVersionUID = 1;

        private StackTraceElement[] stack = new StackTraceElement[0];

        private final Thread thread;
        private final JTable table;

        private final Object LOCK = new Object();

        ThreadPanel(Thread thread) {
            this.thread = thread;
            setLayout(new BorderLayout());
            table = new JTable(new StackModel());
            add(BorderLayout.CENTER, new JScrollPane(table));
        }

        void update() {
            synchronized (LOCK) {
                this.stack = thread.getStackTrace();
                ((AbstractTableModel) table.getModel()).fireTableDataChanged();
            }
        }
    }

    private static StackViewer instance;

    public static StackViewer getInstance() {
        if (instance == null) {
            instance = new StackViewer();
        }
        return instance;
    }

    private final JFrame frame;
    private final JTabbedPane tabPanel;
    private final List<Thread> aliveThreads = new ArrayList<Thread>();
    private final Map<String, ThreadPanel> threadPanels = new HashMap<String, StackViewer.ThreadPanel>();

    private StackViewer() {
        frame = new JFrame("Staroski's VM Inspector");
        tabPanel = new JTabbedPane(SwingConstants.LEFT);
        frame.setContentPane(tabPanel);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(frame, "Do you really want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION,
                                                           JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        frame.setSize(400, 300);
        runMonitor();
    }

    private synchronized void updateView(Map<Thread, StackTraceElement[]> stackTraces) {
        for (Map.Entry<Thread, StackTraceElement[]> entry : stackTraces.entrySet()) {
            Thread thread = entry.getKey();
            if (thread.isAlive()) {
                if (!aliveThreads.contains(thread)) {
                    aliveThreads.add(thread);
                    ThreadPanel panel = new ThreadPanel(thread);
                    threadPanels.put(thread.getName(), panel);
                    tabPanel.addTab(thread.getName(), panel);
                }
            } else {
                if (aliveThreads.contains(thread)) {
                    aliveThreads.remove(thread);
                    tabPanel.remove(threadPanels.get(thread.getName()));
                }
            }
            ThreadPanel threadPanel = (ThreadPanel) tabPanel.getComponent(tabPanel.getSelectedIndex());
            threadPanel.update();
        }
    }

    void runMonitor() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (frame.isVisible()) {
                        updateView(Thread.getAllStackTraces());
                    }
                    Thread.yield();
                }
            }
        }, "Inspector Thread");
        thread.setPriority(Thread.NORM_PRIORITY - 1);
        thread.start();
    }

    void show() {
        frame.setVisible(true);
    }
}