package ru.job4j.mockito;

public class ReplaceAction implements UserAction {
    private final Output output;

    public ReplaceAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.println("=== Edit item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter new name for Item: ");
        Item item = new Item(name);

        if (tracker.replace(id, item)) {
            output.println("Edit item is done.");
        } else {
            output.println(String.format("Item with id=%s not found.", id));
        }
        return true;
    }
}
