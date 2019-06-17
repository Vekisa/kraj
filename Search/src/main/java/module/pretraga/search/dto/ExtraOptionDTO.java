package module.pretraga.search.dto;


import module.pretraga.search.model.ExtraOption;

public class ExtraOptionDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;

    public ExtraOptionDTO() {
    }

    public ExtraOptionDTO(Long id, String name, String description, Double price){
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
    }

    public ExtraOptionDTO(ExtraOption extraOption){
        this(extraOption.getId(), extraOption.getName(), extraOption.getDescription(), extraOption.getPrice());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
