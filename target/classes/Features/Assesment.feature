Feature:  Complete the stage1 Assessment questions

Background: Login the application

Given Login to the application

|Url|Username| Password|
|https://zotuuxt.cyberprism.ie|cyberprism@cri.ie|t1bg*UgJ^~#p|



Scenario: Complete the assessment stage one questions

Then select the specific assessment to answer the stage1 questions

|Assessment_Name|
|AutoTest1|

Then Fill the answers to the stage one questions



Scenario: Complete the assessment stage two questions

Then select the specific assessment to answer the stage2 questions

|Assessment_Name|
|AutoTest1|

Then Fill the answers to the stage two questions
