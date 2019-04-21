package pt.isel.vsdserver.security.session;

import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.bambou.RestFetcher;
import net.nuagenetworks.bambou.RestObject;

import java.util.*;
import java.util.stream.Collectors;

public class FetcherCache<T extends RestObject> {

    private long timeOfGet;
    private final RestFetcher<T> fetcher;

    private final List<T> information;
    private final List<T> removed;
    private final List<T> newInformation;

    /**
     * Registers all the information for a session
     * @param fetcher, the fetcher used to get the information
     * @throws RestException, in case there are errors that happen when getting the information
     */
    public FetcherCache(RestFetcher<T> fetcher) throws RestException {
        this.fetcher = fetcher;

        this.information = this.fetcher.get();
        this.timeOfGet = System.currentTimeMillis();

        this.removed = new LinkedList<>();
        this.newInformation = new LinkedList<>();
    }

    private List<T> getNewInformation(List<T> information){
        if(this.information.containsAll(information))
            return null;

        return information.stream()
                .filter(this.information::contains)
                .collect(Collectors.toList());
    }

    /**
     * Gets removed information from the old list;
     * @param information the old list information;
     * @return the removed information
     */
    private List<T> getRemovedInformation(List<T> information){
        if(information.containsAll(this.information))
            return null;

        return this.information.stream()
                .filter((element) -> !information.contains(element))
                .collect(Collectors.toList());
    }


    private void setInformation(List<T> information){
        this.information.clear();
        this.information.addAll(information);
    }

    /**
     * Updates old information with the new information, it also registers
     * the alterations that happened;
     */
    public void update() throws RestException {
        List<T> newInformation = fetcher.get();
        long time = System.currentTimeMillis();

        List<T> removedChanges = getRemovedInformation(newInformation);
        if(removedChanges != null) {
            this.removed.clear();
            this.removed.addAll(removedChanges);
        }

        List<T> addedChanges = getNewInformation(newInformation);
        if(addedChanges != null){
            this.newInformation.clear();
            this.newInformation.addAll(addedChanges);
        }

        if(addedChanges != null || removedChanges != null)
            this.setInformation(newInformation);

        this.timeOfGet = time;
    }

    public long getTimeOfGet(){ return this.timeOfGet; }

    public List<T> getInformation(){ return this.information; }
}
