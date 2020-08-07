#To build go to main project folder ("calculations") and use: "mvn clean install"
#To run project go to main project folder ("calculations") and use: "mvn spring-boot:run"

#1.Exemplary json to run 
###
{
  "clients": {
    "client": [
      {
        "info": {
          "name": "Tomasz",
          "surname": "Karcznski"
        },
        "balance": {
          "total": "12110",
          "currency": "PLN",
          "date": "01.05.2020"
        },
        "transactions": [
          {
            "type": "income",
            "description": "salary",
            "date": "04.05.2020",
            "value": "7500",
            "currency": "PLN"
          },
          {
            "type": "outcome",
            "description": "mortgage",
            "date": "06.05.2020",
            "value": "1100",
            "currency": "PLN"
          },
          {
            "type": "income",
            "description": "interests",
            "date": "10.05.2020",
            "value": "1700",
            "currency": "PLN"
          },
          {
            "type": "outcome",
            "description": "transfer",
            "date": "11.05.2020",
            "value": "1200",
            "currency": "PLN"
          }
        ]
      },
      {
        "info": {
          "name": "Natalia",
          "surname": "Nowak",
          "country": "Poland"
        },
        "balance": {
          "total": "6750",
          "currency": "PLN",
          "date": "01.05.2020"
        },
        "transactions": [
          {
            "type": "income",
            "description": "salary",
            "date": "04.05.2020",
            "value": "10500",
            "currency": "PLN"
          },
          {
            "type": "outcome",
            "description": "transfer",
            "date": "10.05.2020",
            "value": "1200",
            "currency": "PLN"
          },
          {
            "type": "outcome",
            "description": "transfer",
            "date": "11.05.2020",
            "value": "1050,50",
            "currency": "PLN"
          }
        ]
      }
    ]
  }
}

I've prepared wrapper class to handle such json. That's why I've prepared object structure which can be little bit confusing. My proposition'll be to correct JSON to:

{
    "clients": [
        {
            "info": {
                "name": "Tomasz",
                "surname": "Karcznski"
            },
            "balance": {
                "total": "12110",
                "currency": "PLN",
                "date": "01.05.2020"
            },
            "transactions": [
                {
                    "type": "income",
                    "description": "salary",
                    "date": "04.05.2020",
                    "value": "7500",
                    "currency": "PLN"
                },
                {
                    "type": "outcome",
                    "description": "mortgage",
                    "date": "06.05.2020",
                    "value": "1100",
                    "currency": "PLN"
                },
                {
                    "type": "income",
                    "description": "interests",
                    "date": "10.05.2020",
                    "value": "1700",
                    "currency": "PLN"
                },
                {
                    "type": "outcome",
                    "description": "transfer",
                    "date": "11.05.2020",
                    "value": "1200",
                    "currency": "PLN"
                }
            ]
        },
        {
            "info": {
                "name": "Natalia",
                "surname": "Nowak",
                "country": "Poland"
            },
            "balance": {
                "total": "6750",
                "currency": "PLN",
                "date": "01.05.2020"
            },
            "transactions": [
                {
                    "type": "income",
                    "description": "salary",
                    "date": "04.05.2020",
                    "value": "10500",
                    "currency": "PLN"
                },
                {
                    "type": "outcome",
                    "description": "transfer",
                    "date": "10.05.2020",
                    "value": "1200",
                    "currency": "PLN"
                },
                {
                    "type": "outcome",
                    "description": "transfer",
                    "date": "11.05.2020",
                    "value": "1050,50",
                    "currency": "PLN"
                }
            ]
        }
    ]
}

Thanks to that object structure in code would be easier. An example can be the response object where we've got easier structure. 

#3. What should be improved?
1. I've prepared advice controller but there should be more informative body of exceptions, for example when we've got wrong date format there should be an info about that.
2. In response object we've got BigDecimals representing calculations. BigDecimals has dots inside while in input we send commas. It should be standardized.

#4. How to understand results?
Response to above example:
{
    "clientCalculatedInfo": [
        {
            "name": "Tomasz",
            "surname": "Karcznski",
            "currentBalance": 19010.0,
            "turnout": 11500.0,
            "incomes": 9200.0,
            "expenditures": 2300.0
        },
        {
            "name": "Natalia",
            "surname": "Nowak",
            "currentBalance": 14999.50,
            "turnout": 12750.50,
            "incomes": 10500.0,
            "expenditures": 2250.50
        }
    ]
}
1. Name - represents client's name
2. Surname - represents client's surname
3. CurrentBalance - represents balance on given date with incomes and expenditures
4. Turnout - represents turnouts (sum of incomes and expenditures) without last known balance
5. Incomes - represents incomes without last known balance
5. Expenditures - represents expenditures without last known balance