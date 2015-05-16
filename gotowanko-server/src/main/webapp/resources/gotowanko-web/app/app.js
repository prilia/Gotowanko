'use strict';

// Declare app level module which depends on views, and components
var m = angular.module('gotowankoApp', [
    'ngRoute',
    'ngCookies',
    'gotowankoApp.searchView',
    'gotowankoApp.loginView',
    'gotowankoApp.registrationView',
    'gotowankoApp.userDetailsView'
]);

m.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/search'});
}]);

m.controller('RootController', ['$scope', '$log', '$http', '$location', '$cookieStore',
    function ($scope, $log, $http, $location, $cookieStore) {
        $scope.loggedUser = $cookieStore.get('current.user');

        $scope.isLogged = function () {
            return $scope.loggedUser != undefined
        };

        $scope.isActive = function (viewLocation) {
            var active = (viewLocation === $location.path());
            $log.info($location.path() + ", active: " + active);
            return active;
        };

        $scope.logout = function () {
            $log.info("Logging out");
            $http.delete('/rest/sessions')
                .success(function (data, status, headers, config) {
                    $http.defaults.headers.common.Authorization = undefined;
                    $cookieStore.remove('current.user');
                    $cookieStore.remove('JSESSIONID');
                    $scope.loggedUser = undefined;
                    $log.info(data + " " + status);
                    $scope.alerts = [];
                    $scope.alerts.push({type: 'success', msg: 'Logout successful'});
                    $location.path('/');
                })
                .error(function (data, status, headers, config) {
                    $log.info(data + " " + status);
                    $scope.alerts = [];
                    $scope.alerts.push({type: 'danger', msg: data});
                });
        };
    }]);