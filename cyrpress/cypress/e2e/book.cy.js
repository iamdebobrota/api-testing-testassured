describe("Book API", () => {
  let baseUrl = "https://simple-books-api.click";
  const randomEmail = Math.random().toString(2).substring(5) + "@example.com";

  let token;

  it("should create a new book", () => {
    cy.request({
      method: "POST",
      url: baseUrl + "/api-clients",
      body: {
        clientName: "John Doe",
        clientEmail: "cypress" + randomEmail,
      },
      headers: {
        "Content-Type": "application/json",
      },
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(201);
      const respose = JSON.stringify(response.body);
      token = response.body.accessToken;
      cy.log(respose);
    });
  });

  it("Create the book", () => {
    cy.request({
      method: "POST",
      url: baseUrl + "/orders/",
      body: {
        bookId: 1,
        customerName: "John",
      },
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      failOnStatusCode: false,
    }).then((response) => {
      expect(response.status).to.eq(201);
      expect(response.body.bookId).to.not.be.null;
      expect(response.body.createdAt).to.not.be.null;
      const respose = JSON.stringify(response.body);
      cy.log(respose);
    });
  });
});
