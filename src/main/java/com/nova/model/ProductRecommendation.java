package com.nova.model;

/**
 * ProductRecommendation — What Claude returns to the user
 * Serialized to JSON automatically by Spring Boot (Jackson)
 */
public class ProductRecommendation {

    private String productName;
    private String whyThisProduct;
    private String styleStory;
    private String howToStyle;
    private String alternativeSuggestion;
    private String priceRange;
    private String category;
    private String emoji;

    // ── Constructor ───────────────────────────────
    public ProductRecommendation() {}

    // ── Getters ───────────────────────────────────
    public String getProductName()           { return productName; }
    public String getWhyThisProduct()        { return whyThisProduct; }
    public String getStyleStory()            { return styleStory; }
    public String getHowToStyle()            { return howToStyle; }
    public String getAlternativeSuggestion() { return alternativeSuggestion; }
    public String getPriceRange()            { return priceRange; }
    public String getCategory()              { return category; }
    public String getEmoji()                 { return emoji; }

    // ── Setters ───────────────────────────────────
    public void setProductName(String productName)                   { this.productName = productName; }
    public void setWhyThisProduct(String whyThisProduct)             { this.whyThisProduct = whyThisProduct; }
    public void setStyleStory(String styleStory)                     { this.styleStory = styleStory; }
    public void setHowToStyle(String howToStyle)                     { this.howToStyle = howToStyle; }
    public void setAlternativeSuggestion(String alternativeSuggestion){ this.alternativeSuggestion = alternativeSuggestion; }
    public void setPriceRange(String priceRange)                     { this.priceRange = priceRange; }
    public void setCategory(String category)                         { this.category = category; }
    public void setEmoji(String emoji)                               { this.emoji = emoji; }
}
