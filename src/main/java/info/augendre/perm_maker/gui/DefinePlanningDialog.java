package info.augendre.perm_maker.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import info.augendre.perm_maker.actions.*;
import info.augendre.perm_maker.data.Planning;
import info.augendre.perm_maker.data.Task;
import info.augendre.perm_maker.listeners.AllowEditListener;
import info.augendre.perm_maker.listeners.ListDoubleClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.ResourceBundle;

public class DefinePlanningDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton addTaskButton;
    private JButton removeTaskButton;
    private JList<Task> tasksList;
    private JButton defaultPlanningButton;
    private JButton editTaskButton;
    private JButton createDoodleButton;
    private JButton loadPlanningButton;
    private JButton savePlanningButton;
    private Planning planning;

    public DefinePlanningDialog(Planning planning) {
        this.planning = planning;
        $$$setupUI$$$();
        this.refreshTaskList();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        editTaskButton.setEnabled(false);
        removeTaskButton.setEnabled(false);
        tasksList.addListSelectionListener(new AllowEditListener(editTaskButton));
        tasksList.addListSelectionListener(new AllowEditListener(removeTaskButton));

        buttonOK.addActionListener(e -> onOK());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });
        contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        addTaskButton.addActionListener(new AddTaskAction(this));
        editTaskButton.addActionListener(new EditTaskAction(this));

        removeTaskButton.addActionListener(actionEvent -> {
            if (!tasksList.isSelectionEmpty()) {
                removeTask(tasksList.getSelectedValuesList());
            }
        });
        defaultPlanningButton.addActionListener(new DefaultPlanningAction(this));
        createDoodleButton.addActionListener(new CreateDoodleAction(this, planning));
        tasksList.addMouseListener(new ListDoubleClickListener<>(tasksList));
        loadPlanningButton.addActionListener(new DeserializePlanningAction(this));
        savePlanningButton.addActionListener(new SerializeAction<>(planning));
    }

    private void onOK() {
// add your code here
        dispose();
    }

    public void addTask(Task task) {
        planning.add(task);
        refreshTaskList();
    }

    public Task getSelectedTask() {
        return tasksList.getSelectedValue();
    }

    public java.util.List<Task> getSelectedTasks() {
        return tasksList.getSelectedValuesList();
    }

    public void refreshTaskList() {
        tasksList.setListData(planning.toArray(new Task[0]));
    }

    public void removeTask(Task task) {
        planning.remove(task);
        refreshTaskList();
    }

    public void removeTask(java.util.List<Task> tasksList) {
        for (Task t : tasksList) {
            planning.remove(t);
        }
        refreshTaskList();
    }

    public void resetTasks() {
        planning.clear();
        refreshTaskList();
    }

    public void addAllTasks(Collection<Task> collection) {
        planning.addAll(collection);
        refreshTaskList();
    }

    public boolean isSelectionEmpty() {
        return tasksList.isSelectionEmpty();
    }

    private void createUIComponents() {
        tasksList = new JList<>();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(4, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        defaultPlanningButton = new JButton();
        this.$$$loadButtonText$$$(defaultPlanningButton, ResourceBundle.getBundle("strings").getString("planning-default"));
        panel1.add(defaultPlanningButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createDoodleButton = new JButton();
        createDoodleButton.setText("Doodle");
        panel1.add(createDoodleButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(tasksList);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addTaskButton = new JButton();
        this.$$$loadButtonText$$$(addTaskButton, ResourceBundle.getBundle("strings").getString("task-add"));
        panel2.add(addTaskButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editTaskButton = new JButton();
        this.$$$loadButtonText$$$(editTaskButton, ResourceBundle.getBundle("strings").getString("task-edit"));
        panel2.add(editTaskButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeTaskButton = new JButton();
        this.$$$loadButtonText$$$(removeTaskButton, ResourceBundle.getBundle("strings").getString("task-remove"));
        panel2.add(removeTaskButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        loadPlanningButton = new JButton();
        this.$$$loadButtonText$$$(loadPlanningButton, ResourceBundle.getBundle("strings").getString("planning-load"));
        panel3.add(loadPlanningButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        savePlanningButton = new JButton();
        this.$$$loadButtonText$$$(savePlanningButton, ResourceBundle.getBundle("strings").getString("planning-save"));
        panel3.add(savePlanningButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
