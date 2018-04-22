/**
 * Created by NEUNB_Lisy on 2017/8/1.
 */
app.directive('navPage',function () {
    return{
        restrict:'E',
        template:'<nav aria-label="Page navigation">',
        replace:true,
        transclude:true
    }
});
app.directive('paging',function () {
    return{
        restrict:'E',
        template:'<ul class="pagination">'+
                    '<li>'+
                        '<a ng-click="gotoPage(-1)" aria-label="Previous">'+
                            '<span aria-hidden="true">&laquo;</span>'+
                        '</a>'+
                    '</li>'+
                    '<li ng-repeat="page in pages"><a ng-bind="page" ng-click="gotoPage(page)"></a></li>'+
                    '<li>'+
                        '<a ng-click="gotoPage(-2)" aria-label="Next">'+
                            '<span aria-hidden="true">&raquo;</span>'+
                        '</a>'+
                    '</li>'+
                '</ul>',
        replace:true
    }
});