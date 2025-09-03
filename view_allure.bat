D:
cd "Backup_29july\282025_with working allure report\Transtreme_Stage_Artifact_Id"
D:\Backup_29july\282025_with working allure report\Transtreme_Stage_Artifact_Id>
dir
*** should contain pom file

mvn clean test *** will run as testng through CMD
allure generate allure-results --clean -o allure-report  *** Generate Allure Report and will clean old commands also 
allure open allure-report


 
