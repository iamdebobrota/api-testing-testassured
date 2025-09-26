describe("Get the users", () => {
  let baseUrl = "https://reqres.in/api";

  it("should return the user with page 2", () => {
    cy.request({
      method: "GET",
      url: baseUrl + "/users?page=2",
      headers: {
        "content-type": "application/json",
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      const respose = JSON.stringify(response.body);
      cy.log(respose);
    });
  });
});

describe("Create a user", () => {
  let baseUrl = "https://reqres.in/api";

  it("should create a user", () => {
    cy.request({
      method: "POST",
      url: baseUrl + "/users",
      body: {
        name: "John Doe",
        job: "Software Engineer",
      },
      headers: {
        "content-type": "application/json",
        "x-api-key": "reqres-free-v1",
      },
    }).then((response) => {
      expect(response.status).to.eq(201);
    });
  });

  it("get a single user", () => {
    cy.request({
      method: "GET",
      url: baseUrl + "/users/2",
    }).then((response) => {
      expect(response.status).to.eq(200);
    });
  });
});
