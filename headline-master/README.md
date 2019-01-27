# headline

API usages

URL:{serverip:port}/rest/v1/headlines


Request Body:
country:
The 2-letter ISO 3166-1 code of the country you want to get headlines for. Possible options: ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za . Note: you can't mix this param with the sources param.

category:
The category you want to get headlines for. Possible options: business entertainment general health science sports technology . Note: you can't mix this param with the sources param.

sources:
A List ofstring of identifiers for the news sources or blogs you want headlines from. Use the /sources endpoint to locate these programmatically or look at the sources index. Note: you can't mix this param with the country or category params.

q:
Keywords or a phrase to search for.

pageSize:
int
The number of results to return per page (request). 20 is the default, 100 is the maximum.

page:
int
Use this to page through the results if the total results found is greater than the page size.
