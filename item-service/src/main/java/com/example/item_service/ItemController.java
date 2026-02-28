@RestController
@RequestMapping("/items")
public class ItemController {

    private List<String> items = new ArrayList<>(List.of("Book", "Laptop", "Phone"));

    @GetMapping
    public List<String> getItems() {
        return items;
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody Map<String, String> body) {
        String item = body.get("name");
        items.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added: " + item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getItem(@PathVariable int id) {
        if (id < 0 || id >= items.size())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(items.get(id));
    }
}